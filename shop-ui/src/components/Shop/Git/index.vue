<template>
  <div>
    <svg-icon icon-class="github" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'ShopGit',
  data() {
    return {
      url: 'https://github.com/neusoft-training-Crossboarder-Shop/shop/'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
