module.exports = { 
    devServer: { 
      proxy: { 
        '/api': { 
          target: 'http://localhost:8080/mem',
          changeOrigin: true, 
          pathRewrite: { 
            '^/api': ''
          } 
        } 
      } 
    },
    outputDir: '../backend/public',
  }