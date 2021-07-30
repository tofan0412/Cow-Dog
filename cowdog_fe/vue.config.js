

// Vue3 관련 설정 파일
module.exports = {
    devServer: {
      https: false,
      port: 8083,
      open: true,
      proxy: {
        '/cowdog': {
          target: 'http://localhost:8000/'
        }
      },
      historyApiFallback: true,
      hot: true
    }
  }
  