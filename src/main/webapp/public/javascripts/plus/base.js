define(function (require, exports, module) {
    require('plus/util');
    require('plus/select');
    require('plus/handlebars');
    var grid = require('plus/grid');
    var pager = require('plus/pager');

    var EL_FILTER = $("#J_filter"),
        EL_GRID = $("#J_grid"),
        EL_PAGE = $("#J_page"),
        RECORDS = null;
    //PAGER
    var PAGER = new pager({el: EL_PAGE, pageSize: 10});
    PAGER.on("pageChange", function (page) {
        GRID.config.attr.pageSize = page.config.pageSize;
        GRID.config.attr.curPage = page.pageNum;
        GRID.reload();
    });
    //GRID
    var GRID = new grid({
        url: EL_GRID.attr("data"),
        el: EL_GRID,
        template: $("#" + EL_GRID.attr("template")).html(),
        attr: {
            pageSize: 10,
            curPage: 1
        },
        format: function (data) {
            if (data) {
                $.each(data.results, function (index) {
                    if (index % 2 == 0) {
                        data.results[index].isRow = true;
                    }
                });
            }
            return data;
        }
    });
    $(".filter-more-btn").click(function () {
        $(".filter-more").toggle();
    });
    GRID.on("dataChange", function (data) {
        if (data && RECORDS != data.count || PAGER.pageNum != data.curPage) {
            RECORDS = data.count;
            PAGER.pageNum = data.curPage;
            PAGER.setRecords(RECORDS);
            PAGER.render();
        }
    });
    GRID.on("beforeRender", function (el) {
        $(".operation a", el).click(function () {
            var elm = $(this),
                index = elm.attr("index"),
                evt = elm.attr("evt");
            var data = GRID.Data.results[index] ? GRID.Data.results[index] : index;
            if (evt) {
                GRID.fire(evt, data);
            }
        });
        if (window.parent) {
            var minHeight = window.parent.$(window.parent.document.body).height();
            window.parent.$("#J_content").height(minHeight < $(document.body).height() ? $(document.body).height() : minHeight);
        }
    });
    if (EL_FILTER) {
        //FILTER
        EL_FILTER.on("submit", function () {
            var data = {},
                self = $(this),
                grid = GRID,
                formData = decodeURIComponent(self.serialize());// 将序列号自动编码的汉字解码
            $.each(formData.split("&"), function (index, v) {
                var map = v.split("=");
                data[map[0]] = map[1];
            });
            grid.config.attr.curPage = 1;
            grid.setAttr(data);
            grid.reload();
            return false;
        });
        $("#J_filter select").easySelect({
            width: 120
        });
    }

    $(".j_dropdownSelector").click(function (e) {
        if (e.target.tagName == "A") {
            var elm = $(e.target),
                value = elm.attr("value");
            $(".dropdown-txt", this).html(elm.html());
            $(".dropdown-value", this).val(value);
        }
    });
    $(".date-container").click(function (e) {
        var self = this;
        if ($(e.target).hasClass("icon-calendar")) {
            $(".date-content", self).show();
        }
        if ($(e.target).hasClass("j_date_check")) {
            var list = [];
            $(".date-content", self).hide();
            if ($("#timefrom", self).val() != "") {
                list.push($("#timefrom", self).val());
            }
            if ($("#timeto", self).val() != "") {
                list.push($("#timeto", self).val());
            }
            $(".date-txt", self).html(list.join("&nbsp;至&nbsp;"));
        }
    });
    window.GRIDAPI = GRID;
    module.exports = {
        grid: GRID,
        pager: PAGER
    }
});
