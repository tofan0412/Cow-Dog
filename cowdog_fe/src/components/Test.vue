<template>
  <div>
    <doughnut-chart :percent="50" foregroundColor="#ff4e7e" :visibleValue="true" :valueCountUp="true" />
    <el-button @click="intervalTest">인터벌 테스트1</el-button>
    <div class="game-interface">
      <div class="time" style="text-align:left" v-show="state.timeVisible">남은 시간:{{ state.time }}</div>
      <div class="A-box" @click="selected(state.AData)">
        <div class="A-image">
          <img alt="A사진" :src="require('@/assets/images/logo.png')">
        </div>
        <div class="A-name">
          {{ state.AData }}
        </div>
      </div>
      <div class="B-box" @click="selected(state.BData)">
        <div class="B-image">
          <img alt="B사진" :src="require('@/assets/images/logo.png')">
        </div>
        <div class="B-name">
          {{ state.BData }}
        </div>
      </div>
      <div class="result">
        게임 결과: {{ state.result }}
      </div>
    </div>
  </div>
</template>
<script>
import DoughnutChart from '../../node_modules/vue-doughnut-chart/src/DoughnutChart.vue'
import { reactive } from 'vue'
export default {

  components: {
    DoughnutChart
  },

  setup() {
    const state = reactive({
      cnt: 0,
      gameData: ['Apple', 'Banana', 'Lemon', 'Orange', 'Dog', 'Cat', 'Pizza', 'Chicken'],
      AData: '',
      BData: '',
      result: {},
      time: 4,
      timeVisible: false,
    })
    const intervalTest = function() {
      var game = setInterval(function() {
        if (state.cnt >= parseInt(state.gameData.length / 2)){
          clearInterval(game)
          clearInterval(timer)
          // 게임 및 시간 초기화
          state.timeVisible = false
          state.cnt = 0
          state.time = 4
        } else {
          state.timeVisible = true
          state.AData = state.gameData[state.cnt*2]
          state.BData = state.gameData[state.cnt*2+1]
          state.time = 4
          state.cnt++

        }
      }, 3000)

      var timer = setInterval(() => {
        state.time--
        }, 1000)
    }
    const selected = function (selectedData) {
      state.result[state.cnt] = selectedData
    }
    return { state, intervalTest, selected }
  }
}
</script>
<style scoped>
  .A-box {
    transition: all 0.1s linear;
    width: 30%;
    height: 250px;
    border: 3px solid #ff4e7e;
    border-radius: 10px;
    margin: 20px;
  }
  .B-box {
    transition: all 0.1s linear;
    width: 30%;
    height: 250px;
    border: 3px solid #ff4e7e;
    border-radius: 10px;
    margin: 20px;
  }
  .A-box:hover {
    transform: scale(1.1);
    cursor:pointer;
  }
  .B-box:hover {
    transform: scale(1.1);
    cursor:pointer;
  }
  .A-name {
    font-weight: bold;
  }
  .B-name {
    font-weight: bold;
  }
  img {
    width: 80%;
    border-radius: 10px;
  }
  .font-test {
    font-weight: bold;
  }
</style>
