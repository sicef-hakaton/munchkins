/**
 * Created by MilenCHE on 11/23/2014.
 */
var express = require('express');
var router = express.Router();

var clas = require('../models/class');
var profile = require('../models/profile');
var tag = require('../models/tag');

router.post('/', function(req, res) {
    tag.findOne({'tag': req.body.tag}, function(err, t){
        if(err){
            console.log(err);
            return;
        }

        if(t) {
            res.send(new tag());
            return;
        }


        var newTag = new tag();

        newTag.tag = req.body.tag;
        newTag.classes = req.body.classes;

        newTag.save(function(err, returnTag){
            if(err)
            {
                console.log(err);
            }

            var ret = {"tag_id" : returnTag._id}
            res.send(ret);

        })
    })
});

router.get('/:id', function(req, res){
    var newTag = new tag();

    newTag.tag = req.body.tag;
    newTag.classes = req.body.classes;

    clas.findOne({'_id': req.params._id}, function (err, t){
        if(err){
            console.log(err);
            res.send(tag());
            return;
        }

        if(t)
            res.send(t);

        res.send(tag());
    });
});

module.exports = router;
