<template>
  <div>
    <Card>
      <tables ref="tables" editable searchable search-place="top" v-model="tableData" :columns="columns" @on-delete="handleDelete"/>
    </Card>
  </div>
</template>

<script>
import Tables from '_c/tables'
import { getProductList } from '@/api/data'
export default {
  name: 'product-page',
  components: {
    Tables
  },
  data () {
    return {
      columns: [
        {title: '小程序', key: 'appid', sortable: true},
        {title: '门店', key: 'sid', sortable: true},
        {title: '商品名称', key: 'name', editable: true, sortable: true},
        {title: '商品标题', key: 'title', editable: true, sortable: true},
        {title: '价格', key: 'price', editable: true, sortable: true},
        {title: '描述', key: 'des', editable: true},
        {title: '状态', key: 'status', sortable: true},
        {
          title: '操作',
          key: 'handle',
          button: [
            (h, params, vm) => {
              return h('Poptip', {
                props: {
                  confirm: true,
                  title: '你确定要删除吗?'
                },
                on: {
                  'on-ok': () => {
                    vm.$emit('on-delete', params)
                    vm.$emit('input', params.tableData.filter((item, index) => index !== params.row.initRowIndex))
                  }
                }
              }, [
                h('Button', '自定义删除')
              ])
            }
          ]
        }
      ],
      tableData: []
    }
  },
  methods: {
    handleDelete (params) {
      console.log(params)
    }
  },
  mounted () {
    getProductList().then(res => {
      console.log(res.data)
      // if (res.data.status == '0') {
      //   // 请求成功
      // } else {
      //   Message.error(res.data.msg)
      // }
    })
  }
}
</script>

<style>

</style>
