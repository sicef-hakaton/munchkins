var express = require('express');
var router = express.Router();

var profile = require('../models/profile');

router.get('/:username', function(req, res) {
  profile.findOne({'username': req.params.username}, function(err, profile){
    if(err){
      console.log(err);
      return;
    }
    res.send(profile);
  });
});

router.put('/:username', function(req, res){
  var newProfile = new profile();

  newProfile.first_name = req.body.first_name;
  newProfile.last_name = req.body.last_name;
  newProfile.picture = req.body.picture;
  newProfile.password = req.body.password;
  newProfile.tags_interested = req.body.tags_interested;
  newProfile.tags_teach = req.body.tags_teach;

  profile.findOneAndUpdate({'username': req.params.username}, newProfile, {upsert: true}, function (err, egsistingData){
    if(err){
      console.log(err);
      res.send("{success: false }");
      return;
    }

    res.send("{success: true }");
  });
});

module.exports = router;
