<template>
  <ul>
    <li v-for="group in groups" :key="group.id" @click="selectGroup(group.id)">
      {{ group.name }}
    </li>
  </ul>
</template>

<script>
export default {
  name: 'ChatGroupList',
  data() {
    return {
      groups: [
        { id: 1, name: 'Group 1' },
        { id: 2, name: 'Group 2' },
        { id: 3, name: 'Group 3' },
      ],
    };
  },
  methods: {
    selectGroup(groupId) {
      this.$emit('selectGroup', groupId);
    },
  },
};
</script>

<style scoped>
/* 添加一些群组列表的样式 */
</style>
