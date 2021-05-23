'use strict';

/* Defines application and its dependencies */

var pa165eshopApp = angular.module('pa165esportspApp', ['ngRoute', 'esportsControllers']);
var eshopControllers = angular.module('esportsControllers', []);

/* Configures URL fragment routing, e.g. #/product/1  */
pa165eshopApp.config(['$routeProvider',
    function ($routeProvider) {
        console.log("log")
        $routeProvider.
        when('/home', {templateUrl: 'partials/home.html'}).
        when('/login', {templateUrl: 'partials/login.html'}).
        when('/player/:playerId', {templateUrl: 'partials/player_detail.html', controller: 'PlayerDetailCtrl'}).
        when('/players', {templateUrl: 'partials/players.html', controller: 'PlayersCtrl'}).
        when('/competitions', {templateUrl: 'partials/competitions.html', controller: 'CompetitionsCtrl'}).
        when('/teams', {templateUrl: 'partials/teams.html', controller: 'TeamsCtrl'}).
        when('/teams/:teamId', {templateUrl: 'partials/team_detail.html', controller: 'TeamsDetailCtrl'}).
        when('/teams/name/:teamName', {templateUrl: 'partials/team_detail.html', controller: 'TeamsDetailNameCtrl'}).
        when('/newteam', {templateUrl: 'partials/new_team.html', controller: 'NewTeamCtrl'}).
        when('/newplayer', {templateUrl: 'partials/new_player.html', controller: 'NewPlayerCtrl'}).
        when('/addplayer', {templateUrl: 'partials/add_player.html', controller: 'AddPlayerCtrl'}).
        when('/competition/:competitionId', {templateUrl: 'partials/competition_detail.html', controller: 'CompetitionDetailCtrl'})
    }]);

pa165eshopApp.run(function ($rootScope,$http) {
    // alert closing functions defined in root scope to be available in every template
    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    };
    //change the HTTP Accept header globally to signal accepting the HAL format
    $http.defaults.headers.common.Accept = 'application/hal+json, */*';
});

/* Controllers */
eshopControllers.controller('PlayerDetailCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        // get product id from URL fragment #/product/:productId
        var playerId = $routeParams.playerId;
        $http.get('/esports/api/v2/players/id/' + playerId).then(
            function (response) {
                $scope.player = response.data;
                console.log('AJAX loaded detail of player ' + $scope.player.name);
            },
            function error(response) {
                console.log("failed to load product "+playerId);
                console.log(response);
                $rootScope.warningAlert = 'Cannot load product: '+response.data.message;
            }
        );
        $http.get('/esports/api/v2/players/getPlayerStatistics/id/' + playerId).then(
                    function (response) {
                        $scope.stats = response.data;
                        console.log('AJAX loaded detail of player ' + $scope.player.name);
                    },
                    function error(response) {
                        console.log("failed to load product "+playerId);
                        console.log(response);
                        $rootScope.warningAlert = 'Cannot load product: '+response.data.message;
                    }
                );
    });

eshopControllers.controller('TeamsDetailCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        // get product id from URL fragment #/product/:productId
        var teamId = $routeParams.teamId;
        $http.get('/esports/api/v2/teams/id/' + teamId).then(
            function (response) {
                var team = response.data;
                $scope.team = response.data;
                console.log('AJAX loaded detail of team ' + $scope.team.name);
                loadPlayersTeams($http, team, team['_links'].players.href);
            },
            function error(response) {
                console.log("failed to load team "+teamId);
                console.log(response);
                $rootScope.warningAlert = 'Cannot load team: '+response.data.message;
            }
        );
    });

eshopControllers.controller('TeamsDetailNameCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        // get product id from URL fragment #/product/:productId
        var teamName = $routeParams.teamName;
        $http.get('/esports/api/v2/teams/name/' + teamName).then(
            function (response) {
                var team = response.data;
                $scope.team = response.data;
                console.log('AJAX loaded detail of team ' + $scope.team.name);
                loadPlayersTeams($http, team, team['_links'].players.href);
            },
            function error(response) {
                console.log("failed to load team "+teamId);
                console.log(response);
                $rootScope.warningAlert = 'Cannot load team: '+response.data.message;
            }
        );
    });

eshopControllers.controller('CompetitionDetailCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        // get product id from URL fragment #/product/:productId
        var competitionId = $routeParams.competitionId;
        $http.get('/esports/api/v2/competitions/id/' + competitionId).then(
            function (response) {
                var competition = response.data;
                $scope.competition = competition;
                console.log('AJAX loaded detail of competition ' + $scope.competition.name);
                loadCompetitionTeams($http, competition, competition['_links'].teams.href);
            },
            function error(response) {
                console.log("failed to load competition " + competitionId);
                console.log(response);
                $rootScope.warningAlert = 'Cannot load competition: '+response.data.message;
            }
        );
    });

eshopControllers.controller('CompetitionsCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/esports/api/v2/competitions/').then(
            function (response) {
                $scope.competitions = response.data['_embedded']['competitionDTOList'];
                console.log('AJAX loaded competitions ');
            },
            function error(response) {
                console.log("failed to load competitions ");
                console.log(response);
                $rootScope.warningAlert = 'Cannot load competitions: '+response.data.message;
            }
        );
    });

eshopControllers.controller('TeamsCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/esports/api/v2/teams/').then(
            function (response) {
                $scope.teams = response.data['_embedded']['teamDTOList'];
                console.log('AJAX loaded teams ');
            },
            function error(response) {
                console.log("failed to load teams ");
                console.log(response);
                $rootScope.warningAlert = 'Cannot load teams: '+response.data.message;
            }
        );
    });

eshopControllers.controller('NewTeamCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.team = {
            'name': ''
        };
        // function called when submit button is clicked, creates category on server
        $scope.create = function (team) {
            $http({
                method: 'POST',
                url: '/esports/api/v2/teams/create',
                data: team
            }).then(function success(response) {
                var createdTeam = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new team "' + createdTeam.name + '" was created';
                //change view to list of products
                $location.path("/teams");
            }, function error(response) {
                //display error
                console.log("error when creating team");
                console.log(response);
                switch (response.data.code) {
                    case 'PersistenceException':
                        $rootScope.errorAlert = 'team with the same name already exists ! ';
                        break;
                    case 'InvalidRequestException':
                        $rootScope.errorAlert = 'Sent data were found to be invalid by server ! ';
                        break;
                    default:
                        $rootScope.errorAlert = 'Cannot create team ! Reason given by the server: '+response.data.message;
                        break;
                }
            });
        };
    });

eshopControllers.controller('NewPlayerCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.player = {
            'name': ''
        };
        // function called when submit button is clicked, creates category on server
        $scope.create = function (player) {
            $http({
                method: 'POST',
                url: '/esports/api/v2/players/create',
                data: player
            }).then(function success(response) {
                var createdPlayer = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new player "' + createdPlayer.name + '" was created';
                //change view to list of products
                $location.path("/players");
            }, function error(response) {
                //display error
                console.log("error when creating player");
                console.log(response);
                switch (response.data.code) {
                    case 'PersistenceException':
                        $rootScope.errorAlert = 'player with the same name already exists ! ';
                        break;
                    case 'InvalidRequestException':
                        $rootScope.errorAlert = 'Sent data were found to be invalid by server ! ';
                        break;
                    default:
                        $rootScope.errorAlert = 'Cannot create player ! Reason given by the server: '+response.data.message;
                        break;
                }
            });
        };
    });

eshopControllers.controller('AddPlayerCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        // get product id from URL fragment #/product/:productId
        var playerId = $routeParams.playerId;
        var teamId = $routeParams.teamId;
        $http.get('/esports/api/v2/players/id/' + playerId).then(
            function (response) {
                var player = response.data;
                $scope.player = response.data;

                // function called when submit button is clicked, creates category on server
                $scope.addPlayerToTeam = function (player) {
                    $http({
                        method: 'POST',
                        url: '/esports/api/v2/teams/'+teamId+'/addPlayer/'+playerId,
                        data: teamId, playerId
                    }).then(function success(response) {
                        var createdPlayer = response.data;
                        //display confirmation alert
                        $rootScope.successAlert = 'A new player "' + createdPlayer.name + '" was add into the team';
                        //change view to list of products
                        $location.path("/players");
                    }, function error(response) {
                        //display error
                        console.log("error when creating player");
                        console.log(response);
                        switch (response.data.code) {
                            case 'PersistenceException':
                                $rootScope.errorAlert = 'player with the same name already exists ! ';
                                break;
                            case 'InvalidRequestException':
                                $rootScope.errorAlert = 'Sent data were found to be invalid by server ! ';
                                break;
                            default:
                                $rootScope.errorAlert = 'Cannot create player ! Reason given by the server: '+response.data.message;
                                break;
                        }
                    });
                };
            },
            function error(response) {
                console.log("failed to load product "+playerId);
                console.log(response);
                $rootScope.warningAlert = 'Cannot load product: '+response.data.message;
            }
        );
    });

eshopControllers.controller('PlayersCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/esports/api/v2/players/').then(
            function (response) {
                $scope.players = response.data['_embedded']['playerDTOList'];
                console.log('AJAX loaded players ');
            },
            function error(response) {
                console.log("failed to load players ");
                console.log(response);
                $rootScope.warningAlert = 'Cannot load players: '+response.data.message;
            }
        );
    });




/*
 * Public eshop interface
 */

// helper procedure loading products to category
function loadCompetitionTeams($http, competition, prodLink) {
    $http.get(prodLink).then(function (response) {
        competition.teams = response.data['_embedded']['teamDTOSet'];
        console.log('AJAX loaded ' + competition.teams.length + ' products to category ' + competition.name);
    });
}

function loadPlayerTeams($http, player, prodLink) {
    $http.get(prodLink).then(function (response) {
        player.team = response.data['_embedded']['teamDTOSet'];
    });
}

function loadPlayerStats($http, player, prodLink){

}

function loadPlayersTeams($http, team, prodLink) {
    $http.get(prodLink).then(function (response) {
        team.players = response.data['_embedded']['playerDTOSet'];
    });
}

// /*
//  * Shopping page with all categories and products
//  */
// eshopControllers.controller('ShoppingCtrl', function ($scope, $http) {
//     console.log('calling  /eshop/api/v1/categories/');
//     $http.get('/eshop/api/v1/categories/').then(function (response) {
//         var categories = response.data['_embedded']['categoryDTOList'];
//         console.log('AJAX loaded all categories');
//         $scope.categories = categories;
//         for (var i = 0; i < categories.length; i++) {
//             var category = categories[i];
//             var categoryProductsLink = category['_links'].products.href;
//             loadCategoryProducts($http, category, categoryProductsLink);
//         }
//     });
// });
//
//
// /*
//  * Product detail page
//  */
// eshopControllers.controller('ProductDetailCtrl',
//     function ($scope, $rootScope, $routeParams, $http) {
//         // get product id from URL fragment #/product/:productId
//         var productId = $routeParams.productId;
//         $http.get('/eshop/api/v1/products/' + productId).then(
//             function (response) {
//                 $scope.product = response.data;
//                 console.log('AJAX loaded detail of product ' + $scope.product.name);
//             },
//             function error(response) {
//                 console.log("failed to load product "+productId);
//                 console.log(response);
//                 $rootScope.warningAlert = 'Cannot load product: '+response.data.message;
//             }
//         );
//     });
//
// /*
//  * Category detail page
//  */
//
// eshopControllers.controller('CategoryDetailCtrl', ['$scope', '$routeParams', '$http',
//     function ($scope, $routeParams, $http) {
//         var categoryId = $routeParams.categoryId;
//         $http.get('/eshop/api/v1/categories/' + categoryId).then(function (response) {
//             var category = response.data;
//             $scope.category = category;
//             console.log('AJAX loaded detail of category ' + category.name);
//             loadCategoryProducts($http, category, category['_links'].products.href);
//         });
//     }]);
//
// /*
//  * Administration interface
//  */
//
// /*
//  * Admin Products page
//  */
// function loadAdminProducts($http, $scope) {
//     $http.get('/eshop/api/v1/products').then(function (response) {
//         $scope.products = response.data['_embedded']['productDTOList'];
//         console.log('AJAX loaded all products ');
//     });
// }
// eshopControllers.controller('AdminProductsCtrl',
//     function ($scope, $rootScope, $routeParams, $http) {
//         //initial load of all products
//         loadAdminProducts($http, $scope);
//         // function called when Delete button is clicked
//         $scope.deleteProduct = function (product) {
//             console.log("deleting product with id=" + product.id + ' (' + product.name + ')');
//             $http.delete(product._links.delete.href).then(
//                 function success(response) {
//                     console.log('deleted product ' + product.id + ' on server');
//                     //display confirmation alert
//                     $rootScope.successAlert = 'Deleted product "' + product.name + '"';
//                     //load new list of all products
//                     loadAdminProducts($http, $scope);
//                 },
//                 function error(response) {
//                     console.log("error when deleting product");
//                     console.log(response);
//                     switch (response.data.code) {
//                         case 'ResourceNotFoundException':
//                             $rootScope.errorAlert = 'Cannot delete non-existent product ! ';
//                             break;
//                         default:
//                             $rootScope.errorAlert = 'Cannot delete product ! Reason given by the server: '+response.data.message;
//                             break;
//                     }
//                 }
//             );
//         };
//     });
//
//
// /*
//  * Page with form for creating new product
//  */
// eshopControllers.controller('AdminNewProductCtrl',
//     function ($scope, $routeParams, $http, $location, $rootScope) {
//         //prepare data for selection lists
//         $scope.colors = ['RED', 'GREEN', 'BLUE', 'BLACK'];
//         $scope.currencies = ['CZK', 'EUR', 'USD'];
//         //get categories from server
//         $http.get('/eshop/api/v1/categories/').then(function (response) {
//             $scope.categories = response.data['_embedded']['categoryDTOList'];
//         });
//         //set object bound to form fields
//         $scope.product = {
//             'name': '',
//             'description': '',
//             'categoryId': 1,
//             'price': 0,
//             'color': $scope.colors[1],
//             'currency': $scope.currencies[0]
//         };
//         // function called when submit button is clicked, creates product on server
//         $scope.create = function (product) {
//             $http({
//                 method: 'POST',
//                 url: '/eshop/api/v1/products/create',
//                 data: product
//             }).then(function success(response) {
//                 console.log('created product');
//                 var createdProduct = response.data;
//                 //display confirmation alert
//                 $rootScope.successAlert = 'A new product "' + createdProduct.name + '" was created';
//                 //change view to list of products
//                 $location.path("/admin/products");
//             }, function error(response) {
//                 //display error
//                 console.log("error when creating product");
//                 console.log(response);
//                 switch (response.data.code) {
//                     case 'InvalidRequestException':
//                         $rootScope.errorAlert = 'Sent data were found to be invalid by server ! ';
//                         break;
//                     default:
//                         $rootScope.errorAlert = 'Cannot create product ! Reason given by the server: '+response.data.message;
//                         break;
//                 }
//             });
//         };
//     });
//
// // defines new directive (HTML attribute "convert-to-int") for conversion between string and int
// // of the value of a selection list in a form
// // without this, the value of the selected option is always a string, not an integer
// eshopControllers.directive('convertToInt', function () {
//     return {
//         require: 'ngModel',
//         link: function (scope, element, attrs, ngModel) {
//             ngModel.$parsers.push(function (val) {
//                 return parseInt(val, 10);
//             });
//             ngModel.$formatters.push(function (val) {
//                 return '' + val;
//             });
//         }
//     };
// });
//
// /*
//  * Admin categories
//  */
// eshopControllers.controller('AdminCategoriesCtrl',
//     function ($scope, $rootScope, $routeParams, $http) {
//         //initial load of all categories
//         $http.get('/eshop/api/v1/categories').then(function (response) {
//             $scope.categories = response.data['_embedded']['categoryDTOList'];
//         });
//     });
//
// /*
//  * Page with form for creating new category
//  */
// eshopControllers.controller('AdminNewCategoryCtrl',
//     function ($scope, $routeParams, $http, $location, $rootScope) {
//         //set object bound to form fields
//         $scope.category = {
//             'name': ''
//         };
//         // function called when submit button is clicked, creates category on server
//         $scope.create = function (category) {
//             $http({
//                 method: 'POST',
//                 url: '/eshop/api/v1/categories/create',
//                 data: category
//             }).then(function success(response) {
//                 var createdCategory = response.data;
//                 //display confirmation alert
//                 $rootScope.successAlert = 'A new category "' + createdCategory.name + '" was created';
//                 //change view to list of products
//                 $location.path("/admin/categories");
//             }, function error(response) {
//                 //display error
//                 console.log("error when creating category");
//                 console.log(response);
//                 switch (response.data.code) {
//                     case 'PersistenceException':
//                         $rootScope.errorAlert = 'Category with the same name already exists ! ';
//                         break;
//                     case 'InvalidRequestException':
//                         $rootScope.errorAlert = 'Sent data were found to be invalid by server ! ';
//                         break;
//                     default:
//                         $rootScope.errorAlert = 'Cannot create category ! Reason given by the server: '+response.data.message;
//                         break;
//                 }
//             });
//         };
//     });
