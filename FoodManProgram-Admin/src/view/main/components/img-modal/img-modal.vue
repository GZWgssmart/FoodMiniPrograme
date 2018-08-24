<template>
  <!-- 显示图片的模态窗 -->
  <Modal v-model="imgModal" width="650">
    <p slot="header">
      <span style="font-size:20px;">{{ imgModalTitle }}</span>
    </p>
    <img :src="imgModalImgSrc" style="width: 620px" />
  </Modal>
</template>
<script>
export default {
  name: 'imgModal',
  props: {
    imgModal: false,
    imgModalImgSrc: '',
    imgModalTitle: ''
  },
  data () {
    return {
    }
  },
  computed: {
  },
  methods: {
  }
}
</script>
