/**
    * Created by CosISolutions on 2016-02-12.
 */

$(document).ready(function () {
    'use strict';
    var $win = $(window);
    var $navbar = $('#main-navbar');

    /**
     * Binds scroll of page to navbar
     * Changes opacity levels upon scrolling
     * Takes a look for navbar height since there is nothing behind it at top and hole it's height
     */
    $win.scroll(function () {
        $win.scrollTop() - $navbar.height() <= 0 ? $navbar.css('opacity', '1') : $navbar.css('opacity', '0.8');
    });
});

