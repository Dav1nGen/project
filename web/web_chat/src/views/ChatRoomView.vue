<template>
  <div>
    <button @click="goBack">Back</button>
    <ChatRoom :groupId="groupId" />
  </div>
</template>

<script>
import ChatRoom from '../components/ChatRoom.vue';

export default {
  name: 'ChatRoomView',
  components: {
    ChatRoom,
  },
  computed: {
    groupId() {
      return this.$route.params.id;
    },
  },
  methods: {
    goBack() {
      this.$router.push('/groups');
    },
  },
};
</script>

<style scoped>
button {
  position: absolute;
  top: 10px;
  left: 10px;
  padding: 5px 10px;
  font-size: 16px;
}
</style>
