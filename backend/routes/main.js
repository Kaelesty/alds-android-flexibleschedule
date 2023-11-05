// routes/main.js

const fs = require('fs');

module.exports = function (app) {

    function isFolder(path) {
        return fs.lstatSync(path).isDirectory() && fs.existsSync(path);
    }

    app.get('/', (req, res) => {
        //res.json("Hello React");
        console.log(req.query);
        switch (req.query.type) {
            case "login": {
                fs.readFile('./../data/users.txt', 'utf8', (err, data) => {
                    if (err) {console.log("error: ", err)}
                    if (req.query.name + ":" + req.query.password in data) {
                        res.json(true)
                    } else {
                        res.json(false)
                    }
                })
            }
        }
        /*
        const base = './backend/files/';
        let path = '';

        if ('path' in req.query) {
            path = req.query.path;
        }

        if (isFolder(base + path)) {
            //если папка
            let files = fs.readdirSync(base + path).map(item => {
                const isDir = fs.lstatSync(base + path + '/' + item).isDirectory();
                let size = 0
                if (!isDir) {
                    size = fs.statSync(base + path + '/' + item)
                    console.log(size.size);
                }

                return {
                    name: item,
                    dir: isDir,
                    size: size.size ?? 0
                }
            })
            res.json({
                path: path,
                result: true,
                files: files
            });
        } else {
            console.log('error')
        }
        */
    })
}