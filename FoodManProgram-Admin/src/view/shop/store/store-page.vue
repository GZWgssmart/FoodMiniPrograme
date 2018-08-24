
<template>
<div>
  <Card>
    <!-- 搜索栏 -->
    <Row slot="title" style="height: auto;">
      <i-col span="12">
        <Input v-model="conForm.data.name" placeholder="请输入商铺名称搜搜..." style="width: 200px" @on-enter="searchPage" />
        <span @click="searchPage" style="margin: 0 10px;"><Button type="primary" icon="ios-search">查询</Button></span>
        <i-button type="info" @click="conModal = true" icon="ios-funnel" style="margin: 0 10px;">筛选</i-button>
        <i-button type="default" @click="returnApp" icon="ios-arrow-back" v-if="isApp">返回</i-button>
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
      <form-item label="电话" prop="tel">
        <i-input v-model="conForm.data.tel" placeholder="请输入商家电话"></i-input>
      </form-item>
      <form-item label="状态" prop="query_agent_ids">
        <i-select v-model="conForm.data.status" clearable filterable>
          <i-option v-for="item in statusList" :value="item.value" :key="item.value">{{item.label}}</i-option>
        </i-select>
      </form-item>
    </i-form>
    <div slot="footer">
      <Button type="text" @click="cancel">取消</Button>
      <Button type="text" @click="emptyForm('conForm')">清空</Button>
      <Button type="primary" @click="searchPage">查询</Button>
    </div>
  </modal>

  <!-- 添加和修改模态窗 -->
  <modal v-model="myModal" @on-ok="addOrUpdate()" :width="650" @on-cancel="cancel()" :loading="myLoading" :mask-closable="false">
    <p slot="header">
      <span style="font-size:20px;">{{ modalTitle }}</span>
    </p>
    <i-form ref="myForm" :model="myForm" :rules="ruleValidate" :label-width="120">
      <form-item label="小程序" prop="appid">
        <i-select v-model="myForm.appid" clearable filterable>
          <i-option v-for="item in appList" :value="item.value" :key="item.value">{{item.label}}</i-option>
        </i-select>
      </form-item>
      <form-item label="商家类型" prop="type">
        <i-select v-model="myForm.type" clearable filterable>
          <i-option v-for="item in storeType" :value="item.value" :key="item.value">{{item.label}}</i-option>
        </i-select>
      </form-item>
      <form-item label="名称" prop="name">
        <i-input v-model="myForm.name" placeholder="请输入商家名称"></i-input>
      </form-item>
      <form-item label="商家电话" prop="tel">
        <i-input v-model="myForm.tel" placeholder="请输入商家电话"></i-input>
      </form-item>
      <form-item label="宣传视频地址" prop="video">
        <i-input v-model="myForm.video" placeholder="请输入视频的地址"></i-input>
      </form-item>
      <form-item label="经纬度查询地址：">
        <a href="http://lbs.qq.com/tool/getpoint/" target="_blank">坐标拾取器</a>
      </form-item>
      <form-item label="商家地址" prop="address">
        <i-input v-model="myForm.address" placeholder="请输入商家地址"></i-input>
      </form-item>
      <Row>
        <i-col span="12">
          <form-item label="经度" prop="longitude">
            <i-input v-model="myForm.longitude" placeholder="请输入地图经度"></i-input>
          </form-item>
        </i-col>
        <i-col span="12">
          <form-item label="纬度" prop="latitude">
            <i-input v-model="myForm.latitude" placeholder="请输入地图纬度"></i-input>
          </form-item>
        </i-col>
      </Row>

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
  urls as appUrls
} from '@/view/app/app.js'
import {
  statusList,
  getAllList,
  storeType
} from '@/api/select.js'
import * as table from './store.js'
export default {
  name: 'store-page',
  data () {
    return {
      appid: this.$route.params.appid,
      isApp: false,
      columns: [{
        type: 'selection',
        width: 60,
        align: 'center',
        key: 'id',
        fixed: 'left'
      },
      {
        title: '小程序',
        key: 'appname',
        sortable: true,
        width: 100
      },
      {
        title: '商家名称',
        key: 'name',
        sortable: true,
        width: 200
      },
      {
        title: '商家类型',
        key: 'type',
        sortable: true,
        width: 100
      },
      {
        title: '商家电话',
        key: 'tel',
        sortable: true,
        width: 150
      },
      {
        title: '商家地址',
        key: 'address',
        sortable: true,
        width: 300
      },
      {
        title: '描述',
        key: 'des',
        width: 600
      },
      {
        title: '创建时间',
        key: 'createtime',
        sortable: true,
        width: 200
      },
      {
        title: '状态',
        key: 'status',
        sortable: true,
        width: 100,
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
        width: 250,
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
              style: {
                marginRight: '5px'
              },
              on: {
                click: () => {
                  this.disableOrEnable(row)
                }
              }
            }, text),
            h('Button', {
              props: {
                type: 'success',
                size: 'small',
                icon: 'ios-search'
              },
              on: {
                click: () => {
                  this.searchProduct(row)
                }
              }
            }, '查看商品')
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
      minPrice: '',
      maxPrice: '',

      selectionData: [],

      modalTitle: '',
      myModal: false,
      myLoading: true,
      myForm: table.formParam,
      type: '',
      ruleValidate: {
        name: [{
          required: true,
          message: '请输入商家名称',
          trigger: 'blur'
        }],
        tel: [{
          required: true,
          message: '请输入商家电话'
        }],
        appid: [{
          required: true,
          message: '请选择小程序'
        }],
        type: [{
          required: true,
          message: '请选择商家类型'
        }],
        address: [{
          required: true,
          message: '请输入商家地址'
        }],
        longitude: [{
          required: true,
          message: '请输入经度'
        }],
        latitude: [{
          required: true,
          message: '请输入纬度'
        }]
      },

      statusList: statusList,
      appList: [],
      storeType: storeType
    }
  },
  methods: {
    // 分页查询
    searchPage () {
      const pageUrl = table.urls.pageUrl
      this.tableLoading = true
      if (this.appid !== undefined) {
        this.conForm.data.appid = this.appid
        this.isApp = true
      } else {
        this.isApp = false
        this.conForm.data.appid = ''
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
              if (res.data.status === 0) {
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
        if (res.data.status === 0) {
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
        'type': 1
      }
      getAllList(appUrls.selectUrl, param).then(res => {
        this.appList = res.data.datalist
      })
    },
    // 查看商品
    searchProduct (row) {
      this.$router.push({
        name: '/shop/product_page',
        params: {
          'storeid': row.id
        }
      })
    },
    // 返回小程序页面
    returnApp () {
      this.$router.push({
        name: 'app_page',
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
