const {createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(
        createProxyMiddleware (
            '/mall-admin/**',
            {target: 'http://localhost:9090/'}
            )
    );
};