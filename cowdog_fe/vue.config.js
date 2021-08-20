module.exports = {
  devServer: {
    
    proxy: {
      '/cowdog': {
        target: 'http://localhost:8080'
      }
    },

    historyApiFallback: true,
    hot: true,
    port: 8081
  },
  
  
}
