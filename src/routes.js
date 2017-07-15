angular.module("SkyswitchApp")

.config(function($routeProvider, $locationProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'main/main.html',
        controller: 'MainController',
        title: "Skyswitch Console"
      })
      .when('/dashboard', {
        templateUrl: 'dashboard/dashboard.html',
        controller: 'DashboardController',
        title: 'Dashboard'
      })
      .otherwise('/');

});
