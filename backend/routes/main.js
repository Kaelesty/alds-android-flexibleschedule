// routes/main.js

const fs = require('fs');

module.exports = function (app) {
    const readFileLines = filename =>
        fs
            .readFileSync(filename)
            .toString('UTF8')
            .split('\n');

    app.get('/login', (req, res) => {
        let user = req.query.user + ":" + req.query.password;
        let file = readFileLines('./backend/data/users.txt');
        if (file.find(el => el.slice(0, el.lastIndexOf(':')) == user)) {
            res.json({
                state: true,
                user: file.find(el => (el.slice(0, el.lastIndexOf(':')) == user)).split(':')[2]
            });
        } else {
            res.json({
                state: false,
                user: undefined
            });
        }
    })

    app.get('/registration', (req, res) => {
        let user = req.query.user + ":" + req.query.password + ":" + req.query.login;
        let file = readFileLines('./backend/data/users.txt');
        if (file.find(el => el.slice(0, el.lastIndexOf(':')) == user)) {
            res.json({
                state: false,
                user: undefined
            });
        } else {
            fs.appendFileSync('./backend/data/users.txt', '\n' + user)
            res.json({
                state: true,
                user: req.query.login
            });
        }
    })
}