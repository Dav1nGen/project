<template>
  <div>
    <h1>Login</h1>
    <form @submit.prevent="login">
      <div>
        <input type="text" v-model="username" placeholder="Username" required>
      </div>
      <div>
        <input type="password" v-model="password" placeholder="Password" required>
      </div>
      <div>
        <button type="submit">Login</button>
      </div>
    </form>
  </div>
</template>

<style scoped>
form div {
  margin-bottom: 10px;
}

button {
  width: 10%;
  padding: 10px;
}
</style>


<script>
export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
      socket: null,
    };
  },
  mounted() {
    this.socket = new WebSocket('ws://localhost:8080/ws');

    this.socket.onmessage = (event) => {
      const response = JSON.parse(event.data);
      if (response.type === 'login' && response.success) {
        this.$router.push('/groups');
      } else {
        alert(response.message || 'Login failed');
      }
    };

    this.socket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };
  },
  methods: {
    login() {
      if (this.username && this.password) {
        const loginRequest = {
          type: 'login',
          username: this.username,
          password: this.password,
        };
        this.socket.send(JSON.stringify(loginRequest));
      } else {
        alert('Please enter both username and password');
      }
    },
  },
  beforeDestroy() {
    if (this.socket) {
      this.socket.close();
    }
  },
};
</script>


