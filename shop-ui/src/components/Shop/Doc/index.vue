<template>
  <div>
    <svg-icon icon-class="question" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'ShopDoc',
  data() {
    return {
      url: 'https://github.com/neusoft-training-Crossboarder-Shop/shop/tree/master/'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
