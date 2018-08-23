
<template>
<div>
  <Card>
    <!-- 搜索栏 -->
    <Row slot="title" style="height: auto;">
      <i-col span="12">
        <Row>
          <i-col>
            <Input v-model="conForm.data.name" placeholder="请输入小程序名称搜搜..." style="width: 200px" @on-enter="searchPage" />
            <span @click="searchPage" style="margin: 0 10px;"><Button type="primary" icon="ios-search">查询</Button></span>
            <i-button type="info" @click="conModal = true" icon="ios-funnel" style="margin: 0 10px;">筛选</i-button>
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

  <!-- 添加和修改模态窗 -->
  <modal v-model="myModal" @on-ok="addOrUpdate()" :width="550" @on-cancel="cancel()" :loading="myLoading" :mask-closable="false">
    <p slot="header">
      <span style="font-size:20px;">{{ modalTitle }}</span>
    </p>
    <i-form ref="myForm" :model="myForm" :rules="ruleValidate" :label-width="120">
      <form-item label="名称" prop="name">
        <i-input v-model="myForm.name" placeholder="请输入小程序名称"></i-input>
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
import * as table from './app.js'
export default {
  name: 'product-page',
  data () {
    return {
      columns: [{
        type: 'selection',
        width: 60,
        align: 'center',
        key: 'id',
        fixed: 'left'
      },
      {
        title: '名称',
        key: 'name',
        width: 200,
        sortable: true
      },
      {
        title: '描述',
        key: 'des'
      },
      {
        title: '操作',
        key: 'action',
        fixed: 'right',
        width: 200,
        render: (h, params) => {
          const row = params.row
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
                type: 'success',
                size: 'small',
                icon: 'ios-search'
              },
              on: {
                click: () => {
                  this.searchStore(row)
                }
              }
            }, '查看店铺')
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
          message: '请输入小程序名称',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    // 分页查询
    searchPage () {
      const pageUrl = table.urls.pageUrl
      this.tableLoading = true
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
    // 查看店铺
    searchStore (row) {
      this.$router.push({
        name: '/shop/store_page',
        params: {
          'appid': row.id
        }
      })
    }
  },
  mounted () {
    this.searchPage()
  }
}
</script>

<style>

</style>
