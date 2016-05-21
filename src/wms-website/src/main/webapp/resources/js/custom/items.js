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