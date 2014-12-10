/**
 * Created by MilenCHE on 11/23/2014.
 */
var express = require('express');
var router = express.Router();

var clas = require('../models/class');
var profile = require('../models/profile');
var tag = require('../models/tag');
var comment = require('../models/comment');

router.post('/', function(req, res) {
    comment.findOne({'theme': req.body.theme}, function(err, comments){
        if(err){
            console.log(err);
            return;
        }

        if(comments) {
            res.send(new comment());
            return;
        }


        console.log(req.body);
        var newCommnet = new clas();

        newCommnet.text = req.body.text;
        newCommnet.theme = req.body.theme;
        newCommnet.profile = req.body.username;

        newCommnet.save(function(err, returnComment){
            if(err)
            {
                console.log(err);
            }

            res.send(newComment);

        })
    })
});


// Neverovatno ruzan refference counter.
// Nije bilo vremena za povezivanje async-a

var ret = {
    'comments': []
};

var counted = 1;

function counter(toCount, res)
{
    if(counted < toCount)
    {
        counted++;
        return;
    }

    res.send(ret);

    ret = {
        'comments': []
    };
    counted = 1;
}


router.get('/:theme', function(req, res){

    comment.find({'theme': req.params.theme}, function(err, comments){
        if(err) {
            console.log(err);
            return;
        }

        comments.forEach(function(entry, index){
            console.log(entry);
            profile.findOne({"_id": entry.profile}, function(err, profil)
            {
                ret.comments.push(entry);
                ret.comments[index].profile = profil;
                counter(comments.length, res);
            })
        });

        console.log("String neki!");

        //res.send(ret);
    });
});

module.exports = router;
