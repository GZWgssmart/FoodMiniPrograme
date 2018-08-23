
<template>
<div>
  <Card>
    <!-- 搜索栏 -->
    <Row slot="title" style="height: auto;">
      <i-col span="12">
        <Row>
          <i-col >
            <Input v-model="conForm.data.name" placeholder="请输入商品名称搜搜..." style="width: 200px" @on-enter="searchPage" />
            <span @click="searchPage" style="margin: 0 10px;"><Button type="primary" icon="ios-search">查询</Button></span>
            <i-button type="info" @click="conModal = true" icon="ios-funnel" style="margin: 0 10px;">筛选</i-button>
            <i-button type="default" @click="returnStore" icon="ios-arrow-back" v-if="isStore">返回</i-button>
          </i-col>
        </Row>
      </i-col>
      <!-- 分页组件 -->
      <i-col span="12">
        <div style="float: right;">
          <Page :total="pageTotal" :current="current" :page-size="pageSize" @on-change="changePage" @on-page-size-change="changePageSize" :show-sizer=true show-total></Page>
        </div>
      </i-col>
    </Row>
    <Row>
      <!-- 工具栏 -->
      <i-col>
        <i-button type="success" size="small" @click="showModal('add')" icon="ios-add">添加</i-button>
        &nbsp;
      </i-col>

    </Row>
    <!-- 表格 -->
    <Row class="margin-top-10 searchable-table-con1">
      <Table stripe border :columns="columns" :data="tableData" @on-select="getSelection" @on-sort-change="tableSort" :loading="tableLoading" @on-select-cancel="cancelSelection" @on-select-all="allSelection"></Table>
    </Row>
  </Card>

  <!-- 筛选条件的弹窗 -->
  <modal v-model="conModal" title="筛选条件">
    <i-form ref="conForm" :model="conForm" :label-width="80">
      <form-item label="名称" prop="name">
        <i-input v-model="conForm.data.name" placeholder="请输入商品名称"></i-input>
      </form-item>
      <form-item label="标题" prop="title">
        <i-input v-model="conForm.data.title" placeholder="请输入商品标题"></i-input>
      </form-item>
      <form-item label="状态" prop="query_agent_ids">
        <i-select v-model="conForm.data.status" clearable filterable>
          <i-option v-for="item in statusList" :value="item.value" :key="item.value">{{item.label}}</i-option>
        </i-select>
      </form-item>
      <form-item label="价格" prop="price">
        <Row>
          <i-col span="11">
            <i-input v-model="conForm.data.pricestart" placeholder="请输入商品价格"></i-input>
          </i-col>
          <i-col span="2">
            <div style="text-align: center;">-</div>
          </i-col>
          <i-col span="11">
            <i-input v-model="conForm.data.priceend" placeholder="请输入商品价格"></i-input>
          </i-col>
        </Row>
      </form-item>
    </i-form>
    <div slot="footer">
      <Button type="text" @click="cancel">取消</Button>
      <Button type="text" @click="emptyForm('conForm')">清空</Button>
      <Button type="primary" @click="searchPage">查询</Button>
    </div>
  </modal>

  <!-- 添加和修改模态窗 -->
  <modal v-model="myModal" @on-ok="addOrUpdate()" :width="750" @on-cancel="cancel()" :loading="myLoading" :mask-closable="false">
    <p slot="header">
      <span style="font-size:20px;">{{ modalTitle }}</span>
    </p>
    <i-form ref="myForm" :model="myForm" :rules="ruleValidate" :label-width="120">
      <row>
        <i-col span="12">
          <form-item label="小程序" prop="appid">
            <i-select v-model="myForm.appid" clearable filterable @on-change="getAllStoreSelectByApp">
              <i-option v-for="item in appList" :value="item.value" :key="item.value">{{item.label}}</i-option>
            </i-select>
          </form-item>
        </i-col>
        <i-col span="12">
          <form-item label="店铺" prop="sid">
            <i-select v-model="myForm.sid" clearable filterable>
              <i-option v-for="item in storeList" :value="item.value" :key="item.value">{{item.label}}</i-option>
            </i-select>
          </form-item>
        </i-col>
      </row>
      <row>
        <i-col span="12">
          <form-item label="名称" prop="name">
            <i-input v-model="myForm.name" placeholder="请输入商品名称"></i-input>
          </form-item>
        </i-col>
        <i-col span="12">
          <form-item label="标题" prop="title">
            <i-input v-model.number="myForm.title" placeholder="请输入商品标题"></i-input>
          </form-item>
        </i-col>
      </row>
      <form-item label="价格" prop="price">
        <i-input v-model="myForm.price" placeholder="请输入商品价格（元）"></i-input>
      </form-item>

      <form-item label="描述" prop="des">
        <i-input v-model="myForm.des" type="textarea" :autosize="{minRows: 3,maxRows: 5}" placeholder="请输入商品描述"></i-input>
      </form-item>
    </i-form>
  </modal>
</div>
</template>

<script>
import '@/styles/common.less'
import '@/styles/table.less'
import {
  ajaxFun
} from '@/api/common.js'
import {
  statusList,
  getAllList,
  getStoreAllListByAppId
} from '@/api/select.js'
import {
  urls as appUrls,
  formParam as appFormParam
} from '@/view/app/app.js'
import {
  urls as storeUrls
} from '@/view/shop/store/store.js'
import * as table from './product.js'
export default {
  name: 'product-page',
  data () {
    return {
      storeid: this.$route.params.storeid,
      isStore: false,
      columns: [{
        type: 'selection',
        width: 60,
        align: 'center',
        key: 'id',
        fixed: 'left'
      },
      {
        title: '小程序',
        key: 'appid',
        sortable: true
      },
      {
        title: '门店',
        key: 'sid',
        sortable: true
      },
      {
        title: '商品名称',
        key: 'name',
        sortable: true
      },
      {
        title: '商品标题',
        key: 'title',
        sortable: true
      },
      {
        title: '价格',
        key: 'price',
        sortable: true
      },
      {
        title: '描述',
        key: 'des'
      },
      {
        title: '状态',
        key: 'status',
        sortable: true,
        render: (h, params) => {
          const row = params.row
          const color = row.status === '禁用' ? 'error' : 'success'
          const text = row.status
          return h('div', [
            h('Tag', {
              props: {
                color: color,
                size: 'small'
              }
            }, text)
          ])
        }
      },
      {
        title: '操作',
        key: 'action',
        fixed: 'right',
        width: 160,
        render: (h, params) => {
          const row = params.row
          const color = row.status === '禁用' ? 'info' : 'error'
          const text = row.status === '禁用' ? '启用' : '禁用'
          const icon = row.status === '禁用' ? 'ios-checkmark' : 'ios-close'
          return h('div', [
            h('Button', {
              props: {
                type: 'primary',
                size: 'small',
                icon: 'ios-create'
              },
              style: {
                marginRight: '5px'
              },
              on: {
                click: () => {
                  this.showModal(row)
                }
              }
            }, '编辑'),
            h('Button', {
              props: {
                type: color,
                size: 'small',
                icon: icon
              },
              on: {
                click: () => {
                  this.disableOrEnable(row)
                }
              }
            }, text)
          ])
        }
      }
      ],
      tableData: [],
      tableLoading: false,
      initTable: [],
      pageTotal: 0,
      current: table.conParam.page,
      pageSize: table.conParam.rows,

      conModal: false,
      conLoading: false,
      conForm: table.conParam,

      selectionData: [],

      modalTitle: '',
      myModal: false,
      myLoading: true,
      myForm: table.formParam,
      type: '',
      ruleValidate: {
        name: [{
          required: true,
          message: '请输入商品名称',
          trigger: 'blur'
        }],
        title: [{
          required: true,
          message: '请输入商品标题',
          trigger: 'blur'
        }],
        price: [{
          required: true,
          message: '请输入商品价格',
          trigger: 'blur'
        }],
        appid: [{
          required: true,
          message: '请选择小程序'
        }],
        sid: [{
          required: true,
          message: '请选择店铺'
        }]
      },

      statusList: statusList,
      appList: [],
      storeList: [],
      storeListSelect: []
    }
  },
  methods: {
    // 分页查询
    searchPage () {
      const pageUrl = table.urls.pageUrl
      this.tableLoading = true
      if (this.storeid !== undefined) {
        this.conForm.data.sid = this.storeid
        this.isStore = true
      } else {
        this.isStore = false
      }
      var params = this.conForm
      ajaxFun(pageUrl, params, 'post').then(res => {
        this.tableData = res.data.datalist
        this.pageTotal = res.data.totalrecords
        this.tableLoading = false
        if (this.conModal) {
          this.conModal = false
        }
      })
      // 初始化表格选择框
      this.selectionData = []
    },
    // 表格排序
    tableSort (column) {
      const key = column.column.key
      const order = column.order
      if (order !== 'normal') {
        table.conParam.sidx = key
        table.conParam.sord = order
        this.searchPage()
      }
    },
    // 选择页码
    changePage (index) {
      table.conParam.page = index
      this.searchPage()
    },
    // 验证表格多选框选择的数据
    valiSelection () {
      if ([] === this.selectionData) {
        this.$Message.warning('请选择一条数据')
        return false
      }
      if (this.selectionData.length !== 1) {
        this.$Message.warning('请选择一条数据')
        return false
      }
      return true
    },
    // 选择每页数量
    changePageSize (size) {
      table.conParam.rows = size
      this.searchPage()
    },
    // 点击表格多选框获取id
    getSelection (selection, row) {
      this.selectionData = selection
    },
    // 点击取消多选框重新赋值给已选行
    cancelSelection (selection, row) {
      this.selectionData = selection
    },
    // 点击全选重新赋值给已选行
    allSelection (selection) {
      this.selectionData = selection
    },
    // 取消
    cancel () {
      this.$Message.info('取消')
      if (this.conModal) {
        this.conModal = false
      }
    },
    // 清空表单
    emptyForm (form) {
      this.$refs[form].resetFields()
    },
    // 打开模态窗 add -> 添加模态窗
    showModal (row) {
      this.type = row
      if (row === 'add') {
        this.emptyForm('myForm')
        this.myForm = table.formParam
        this.modalTitle = '添加'
        this.myModal = true
        this.myLoading = true
      } else {
        const status = row.status
        if (status === '禁用') {
          this.$Message.warning('不能对禁用的数据进行编辑')
          return
        }
        this.myForm = row
        this.modalTitle = '修改'
        this.myModal = true
        this.myLoading = true
      }
    },
    // 添加修改
    addOrUpdate () {
      var urls = ''
      if (this.type !== '') {
        if (this.type === 'add') {
          urls = table.urls.addUrl
        } else {
          urls = table.urls.updateUrl
        }
      }

      if (urls !== '') {
        this.$refs['myForm'].validate((valid) => {
          if (valid) {
            ajaxFun(urls, this.myForm, 'post').then(res => {
              if (res.data.status === '0') {
                this.$Message.success(this.modalTitle + '成功')
              } else {
                this.$Message.error(this.modalTitle + '失败')
              }
              this.myLoading = false
              this.myModal = false
              this.emptyForm('myForm')
              this.searchPage()
            })
          } else {
            setTimeout(() => {
              this.myLoading = false
              this.$Message.error(this.modalTitle + '失败')
              this.$nextTick(() => {
                this.myLoading = true
              })
            }, 1000)
          }
        })
      }
    },
    // 启用禁用
    disableOrEnable (row) {
      var url = table.urls.statusUrl
      const text = row.status === '禁用' ? '启用' : '禁用'
      ajaxFun(url, row, 'post').then(res => {
        if (res.data.status === '0') {
          this.$Message.success(text + '成功')
        } else {
          this.$Message.error(res.data.msg)
        }
        this.searchPage()
      })
    },
    // 获取所有小程序
    getAllAppSelect () {
      const param = {
        'type': 0
      }
      getAllList(appUrls.selectUrl, param).then(res => {
        this.appList = res.data.datalist
      })
    },
    // 根据小程序获取商铺
    getAllStoreSelectByApp (appId) {
      appFormParam.id = appId
      getStoreAllListByAppId(storeUrls.selectByAppIdUrl, appFormParam).then(res => {
        this.storeList = res.data.datalist
        console.log(this.storeList)
      })
    },
    // 返回商铺页面
    returnStore () {
      this.$router.push({
        name: '/shop/store_page',
        params: ''
      })
    }
  },
  mounted () {
    this.searchPage()
    this.getAllAppSelect()
  }
}
</script>

<style>

</style>
