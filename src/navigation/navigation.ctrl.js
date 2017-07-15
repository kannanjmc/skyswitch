angular.module("SkyswitchApp")

.controller("NavigationController", function($scope, $mdSidenav, $route) {
  $scope.routeInfo = $route;

  $scope.menu = [
    {
      link : '#!/dashboard',
      title: 'Dashboard',
      icon: 'dashboard'
    },
    {
      link : '',
      title: 'Servers',
      icon: 'group'
    },
    {
      link : '',
      title: 'Features',
      icon: 'message'
    }
  ];

  $scope.toggleMenu = function () {
    $mdSidenav("menu").toggle();
  };
});
