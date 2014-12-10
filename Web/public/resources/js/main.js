var app = angular.module('app', [
  'ngRoute',
    'ui.select2'
]);



app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider

    .when("/", {
          templateUrl: "/modules/newsFeeds/feed_home.html",
          controller: "FeedsCtrl"})
    .when("/teacher", {
          templateUrl: "/modules/teachers/teacher_home.html",
          controller: "TeacherCtrl"})
    .when("/profiles", {
          templateUrl: "/modules/profile/profile_home.html",
          controller: "ProfileCtrl"})
      .when("/study_buddy", {
          templateUrl: "/modules/study_buddy/sbuddy_home.html",
          controller: "SBuddyCtrl"})
      .when("/about", {
          templateUrl: "/modules/about/about_home.html",
          controller: "AboutCtrl"})

    .otherwise("/404", {
          templateUrl: "/resources/templates/404.html",
          controller: "ErrorCtrl"});
}]);

(function($){


})(jQuery);