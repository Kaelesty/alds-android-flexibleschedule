const express = require('express');
const app = express();
const port = 8080;

app.use(function(req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST');
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,constent-tupe');
    res.setHeader('Access-Control-Allow-Credentials', true);
    next();
});


require('./routes/index')(app);

app.listen(port, () => {
    console.log('start')
})