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
      link : '#!/applications',
      title: 'Applications',
      icon: 'apps',
    },
    {
      link : '#!/features',
      title: 'Features',
      icon: 'transform'
    }
  ];

  $scope.toggleMenu = function () {
    $mdSidenav("menu").toggle();
  };
});
