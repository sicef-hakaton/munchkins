/**
 * Created by MilenCHE on 11/22/2014.
 */

var config = require('./database.js')
var MongoClient = require('mongodb').MongoClient;
var db_singleton = null;


//example usage
/*
 getConnection(function (err, db) {
 if (err) {
 console.log(err);
 return;
 }

 //do your shit here

 }*/
var getConnection = function getConnection(callback) {
    if (db_singleton) {
        callback(null, db_singleton);
    }
    else {

        MongoClient.connect(config.url, function (err, db) {

            if (err)
                console.log("Error creating new connection " + err);
            else {
                db_singleton = db;
                console.log("created new connection");

            }
            callback(err, db_singleton);
            return;
        });
    }
}

module.exports = getConnection;