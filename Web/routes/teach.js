/**
 * Created by MilenCHE on 11/23/2014.
 */
var express = require('express');
var router = express.Router();

var clas = require('../models/class');
var profile = require('../models/profile');
var tag = require('../models/tag');

router.post('/class', function(req, res) {
    console.log(req.body);
    clas.findOne({'theme': req.body.theme}, function(err, classes){
        if(err){
            console.log(err);
            return;
        }

        if(classes) {
            res.send(new clas());
            return;
        }


        console.log(req.body);
        var newClass = new clas();

        newClass.teacher = req.body.teacher;
        newClass.theme = req.body.theme;
        newClass.tags = req.body.tags;
        newClass.date_time = req.body.date_time;
        newClass.max_students = req.body.max_students;
        newClass.students = req.body.students;

        newClass.save(function(err, returnClass){
            if(err)
            {
                console.log(err);
            }

            var ret = {"class_id" : returnClass._id}
            res.send(ret);

        })
    })
});

router.put('/class', function(req, res){
    var newClass = new clas();

    newClass.tags = req.body.tags;
    newClass.date_time = req.body.date_time;
    newClass.max_students = req.body.max_students;
    newClass.students = req.body.students;

    console.log(req.body);

    clas.findOneAndUpdate({'_id': req.body._id}, newClass.toObject(), {upsert: true}, function (err, egsistingData){
        if(err){
            console.log(err);
            res.send("{success: false }");
            return;
        }

        res.send("{success: true }");
    });
});

// Neverovatno ruzan refference counter.
// Nije bilo vremena za povezivanje async-a


var ret = {
    "maxResults": 0,
    "results": []
}
var counted = 1;

function counter(toCount, res)
{
    console.log("BrojiM! " +  toCount + counted);
    if(counted < toCount)
    {
        counted++;
        return;
    }

    res.send(ret);

    ret = {
        "maxResults": 0,
        "results": []
    }
    counted = 1;
}

router.get('/class/:username', function(req, res){
    profile.findOne({'username': req.params.username}, function(err, user){


        console.log(user.tags_interested);
        user.tags_interested.forEach(function(entry){
            console.log("Entry: " + entry);
            tag.find({'tag': entry}, function(err, found){
                console.log(found);
                found.forEach(function(fnd){
                    ret.maxResults += fnd.classes.length;
                    fnd.classes.forEach(function(cls, index) {
                        console.log(cls);
                        clas.findOne({'_id': cls}, function (err, aclass) {
                            ret.results.push(aclass);
                            //counter(ret.maxResults, res);
                            counter(ret.maxResults, res);
                        })

                    })

                })


               // res.send(ret);
            });
        });


    });
});

module.exports = router;
