/**
 * Created by MilenCHE on 11/23/2014.
 */
var mongoose = require('mongoose');

/* Schema for profile data. */

var tagSchema = mongoose.Schema({
    tag: String,
    classes: [String]
});

module.exports = mongoose.model("Tag", tagSchema);