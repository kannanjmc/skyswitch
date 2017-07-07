angular.module("SkyswitchApp")

.controller("NavigationController", function($scope) {
  $scope.menu = [
    {
      link : '/dashboard',
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
});
