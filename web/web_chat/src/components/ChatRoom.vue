<template>
  <div>
    <div class="chat-box">
      <div v-for="message in messages" :key="message.id">
        <strong>{{ message.username }}:</strong> {{ message.text }}
      </div>
    </div>
    <div class="input-box">
      <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type a message">
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChatRoom',
  props: {
    groupId: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      newMessage: '',
      messages: [],
      socket: null,
    };
  },
  mounted() {
    this.connect();
  },
  methods: {
    connect() {
      this.socket = new WebSocket('ws://localhost:8080/ws');// WebSocket服务器地址

      this.socket.onmessage = event => {
        const message = JSON.parse(event.data);
        this.messages.push(message);
      };

      this.socket.onopen = () => {
        console.log('WebSocket connection established');
      };

      this.socket.onclose = () => {
        console.log('WebSocket connection closed');
      };

      this.socket.onerror = error => {
        console.error('WebSocket error:', error);
      };
    },
    sendMessage() {
      if (this.newMessage.trim()) {
        const message = {
          groupId: this.groupId,
          username: 'You',
          text: this.newMessage.trim(),
        };
        this.socket.send(JSON.stringify(message));
        this.newMessage = '';
      }
    },
  },
};
</script>

<style scoped>
.chat-box {
  height: 300px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}
.input-box {
  display: flex;
}
.input-box input {
  flex: 1;
  padding: 10px;
}
.input-box button {
  padding: 10px;
}
</style>
