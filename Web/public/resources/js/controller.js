/**
 * Created by JoleX on 23-Nov-2014.
 */
var tags = ['Java', 'Android', 'AngularJS', 'MongoDB', 'NodeJS', 'C++'];
app.controller('FeedsCtrl', function ($scope, $location, $http ) {

    $scope.feeds = [
    {
        'title' : 'Hakaton results',
        'body' : 'Today we\'re announcing the winner',
        'user' :
            {'username': 'mita', 'rate' : 200}
        ,
        'tags': ['#Android','#Java'],
        'comments': [
            {'username': 'hero29', 'comment' : 'This is awesome'},
            {'username': 'hakaton', 'comment' : 'I think all be fine'},
            {'username': 'user1', 'comment' : 'So? How is the winner...?'}
        ]
    },    {
        'title' : 'Some title',
        'body' : 'Some text',
        'user' :
            {'username': 'mira', 'rate' : 200}
        ,
        'tags': ['#tag1','#tag2'],
        'comments': [
            {'username': 'hero29', 'comment' : 'This is awesome'},
            {'username': 'hakaton', 'comment' : 'I think all be fine'},
            {'username': 'user1', 'comment' : 'So? How is the winner...?'}
        ]
    },
    {'title': 'Some title'}]
    ;



});

app.controller('TeacherCtrl', function ($scope, $location, $http ) {
    $('#datepicker').datepicker({
        autoclose:true
    })

    $scope.list_of_tags = ["Android"]
    $scope.select2Options = {
        'multiple': true,
        'simple_tags': true,
        'tags': tags
    };
    $scope.submit = function(){
        var data = this.submit.arguments[0];
        data.teacher = "mita";
        data.tags = this.list_of_tags;
        var req = {
            method: 'POST',
            url: '/teach/class',
            data: data
        }

        $http(req).success(function(res){
            alert("Successful!!");
        });
    }


});

app.controller('ProfileCtrl', function ($scope, $location, $http ) {

    $http.get('/profile/mita').success(function(data){
        $scope.p = data ;
    });

    $scope.interested_tags = ["Android","C++"];
    $scope.teach_tags = ["Android"];

    $scope.select2OptionsI = {
        'multiple': true,
        'simple_tags': true,
        'tags': tags
    };
    $scope.select2OptionsT = {
        'multiple': true,
        'simple_tags': true,
        'tags': tags
    };
    $scope.submit = function(){
        var data = this.submit.arguments[0];
        data.tags_interested = $scope.interested_tags;
        data.tags_teach = $scope.teach_tags;

        var req = {
            method: 'PUT',
            url: '/profile/mita',
            data: data
        }

        $http(req).success(function(res){
            alert("Successful!");
        });
    }
    $scope.rate = 200;
});

app.controller('SBuddyCtrl', function ($scope, $location, $http ) {

});
app.controller('AboutCtrl', function ($scope, $location, $http ) {

});
app.controller('ErrorCtrl', function ($scope, $location, $http ) {

});