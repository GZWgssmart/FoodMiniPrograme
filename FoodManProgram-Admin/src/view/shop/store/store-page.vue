<template>
  <div>
    <Card>
      <tables ref="tables" editable searchable search-place="top" v-model="tableData" :columns="columns" @on-delete="handleDelete"/>
    </Card>
  </div>
</template>

<script>
import Tables from '_c/store'
import { ajaxFun } from '@/api/common'
export default {
  name: 'store_page',
  components: {
    Tables
  },
  data () {
    return {
      columns: [
        {title: '店铺类型', key: 'type', sortable: true},
        {title: '名称', key: 'name', sortable: true},
        {title: '地址', key: 'email', editable: true},
        {title: '介绍', key: 'des', editable: true},
        {title: '电话', key: 'tel', editable: true},
        {
          title: 'Logo',
          key: 'log',
          render: (h, params) => {
      			const row = params.row;
      			const url = row.log;
      			return h('img', {
      				attrs: {
      					src: url
      				},
      				style: {
      					width: "161.5px",
      					height: "44px"
      				}
      			}, '');
      		}
        },
        {
          title: '视频',
          key: 'video',
          render: (h, params) => {
      			const row = params.row;
      			const url = row.video;
      			return h('a', {
      				attrs: {
      					href: url
      				}
      			}, url);
      		}
        },
        {title: '状态', key: 'status', editable: false, sortable: true},
        {title: '添加时间', key: 'createtime', sortable: true},
        {
          title: '操作',
          key: 'handle',
          options: ['delete'],
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
              })
            }
          ]
        }
      ],
      tableData: [],
      pageUrl: ''
    }
  },
  methods: {
    handleDelete (params) {
      console.log(params)
    }
  },
  mounted () {
    const url = this.pageUrl;
    const param = "";
    ajaxFun(url, param, 'post').then(res => {
      this.tableData = res.data
    })
  }
}
</script>

<style>

</style>
