<template>
  <div>
    <h1>Chat Groups</h1>
    <ChatGroupList @selectGroup="selectGroup" />
  </div>
</template>

<script>
import ChatGroupList from '../components/ChatGroupList.vue';

export default {
  name: 'ChatGroups',
  components: {
    ChatGroupList,
  },
  methods: {
    selectGroup(groupId) {
      this.$router.push(`/chat/${groupId}`);
    },
  },
};
</script>

<style scoped>
/* 添加一些群组列表界面的样式 */
</style>
