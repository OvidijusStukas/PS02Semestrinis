$(function() {

    setTimeout(toggleLeft,500);
    setTimeout(toggleRight,1500);

    function toggleRight() {
        var $rightSidebar = $("#right-sidebar");
        var $mainContainer = $("#item-gallery");

        if ($rightSidebar.css("margin-right") == "0px") {
            $rightSidebar.animate({'margin-right': '-20%'});

            var mainWidth = Math.round($mainContainer.width() / $('body').width() * 100);
            mainWidth = parseInt(mainWidth) +20;
            mainWidth += "%";

            $mainContainer.animate({"width": mainWidth});
        }
        else {
            $rightSidebar.animate({'margin-right': '0'});

            var mainWidth = Math.round($mainContainer.width() / $('body').width() * 100);
            mainWidth = parseInt(mainWidth) - 20;
            mainWidth += "%";

            $mainContainer.animate({"width": mainWidth});
        }
    }

    function toggleLeft() {
        var $leftSidebar = $("#left-sidebar");
        var $mainContainer = $("#item-gallery");

        if ($leftSidebar.css("margin-left") == "0px") {
            $leftSidebar.animate({'margin-left': '-20%'});

            var mainWidth = Math.round($mainContainer.width() / $('body').width() * 100);
            mainWidth = parseInt(mainWidth) + 20;
            mainWidth += "%";

            $mainContainer.animate({left: 0,width: mainWidth});
        }
        else {
            $leftSidebar.animate({'margin-left': '0'});

            var mainWidth = Math.round($mainContainer.width() / $('body').width() * 100);
            mainWidth = parseInt(mainWidth) - 20;
            mainWidth += "%";

            $mainContainer.animate({left: "20%",width: mainWidth});
        }
    }

    $("#right-sidebar-control").bind("click", function () {
        toggleRight();
    });

    $("#left-sidebar-control").bind("click", function () {
        toggleLeft();
    });
});

(function ($) {
    "use strict";
    var $search = $("#search");

    $search.keyup(function (e) {
        if (e.keyCode == 13) {
            document.getElementById("search-href").click();
        }
    });
}(jQuery));

function removeGroup(groupId) {
    var $group = $("#group-"+groupId);
    "use strict";
    $.ajax({
        dataType: "json",
        contentType: "application/json",
        url: "items/removeGroup?groupId="+groupId,
        success: function(data) {
           if(data === true) {
             $("#group-list").notify("Removed successfully", "success");
             $group.remove();
           }
           else
             $group.children().last().notify("Remove failed", "error");
        },
        error: function () {
            $group.children().last().notify("Remove failed", "error");
        }
    })
}

function remove(id) {
    var $item = $("#item-"+id);
    "use strict";
    $.ajax({
        dataType: "json",
        contentType: "application/json",
        url: "items/remove?id="+id,
        success: function(data) {
            if(data === true) {
                $("#add-item-link").notify("Removed successfully", "success");
                $item.remove();
            }
            else
                $item.children().first().notify("Remove failed", "error");
        },
        error: function () {
            $item.children().first().notify("Remove failed", "error");
        }
    })
}

function searchItems(assetId) {
    "use strict";
    window.location = "/items/search?assetId=" + assetId + "&search=" + $("#search").val();
}