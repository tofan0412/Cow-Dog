module.exports = {
  devServer: {
    
    proxy: {
      '/cowdog': {
        target: 'http://i5b104.p.ssafy.io'
      }
    },
    https:true,
    historyApiFallback: true,
    hot: true,
    port: 8081
  },
  
  
}
