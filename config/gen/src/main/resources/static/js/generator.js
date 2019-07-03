$(function () {
    $("#jqGrid").jqGrid({
        url: 'sys/generator/list',
        datatype: "json",
        colModel: [
            {label: '表名', name: 'tableName', width: 100, key: true},
            {label: 'Engine', name: 'engine', width: 70},
            {label: '表备注', name: 'tableComment', width: 100},
            {label: '创建时间', name: 'createTime', width: 100}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50, 100, 200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    /*建立模态框对象*/
    var modalBox = {};
    /*获取模态框*/
    modalBox.modal = document.getElementById("myModal");
    /*获得trigger按钮*/
    modalBox.triggerBtn = document.getElementById("triggerBtn");
    /*获得关闭按钮*/
    modalBox.closeBtn = document.getElementById("closeBtn");
    /*模态框显示*/
    modalBox.show = function () {
        console.log(this.modal);
        this.modal.style.display = "block";
    }
    /*模态框关闭*/
    modalBox.close = function () {
        this.modal.style.display = "none";
    }
    /*当用户点击模态框内容之外的区域，模态框也会关闭*/
    modalBox.outsideClick = function () {
        var modal = this.modal;
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    }
    /*模态框初始化*/
    modalBox.init = function () {
        var that = this;
        this.triggerBtn.onclick = function () {
            var tableNames = getSelectedRows();
            if (tableNames == null) {
                return;
            }
            that.show();
        }
        this.closeBtn.onclick = function () {
            that.close();
        }
        this.outsideClick();
    }
    modalBox.init();
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            tableName: null
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'tableName': vm.q.tableName},
                page: 1
            }).trigger("reloadGrid");
        },
        generator: function () {
            var codeUrl = $("#codeUrl").val();//java代码路径
            var xmlUrl = $("#xmlUrl").val();//xml文件路径
            var vueUrl = $("#vueUrl").val();//vue文件路径
            var tableNames = getSelectedRows();
            location.href = "sys/generator/code?tables=" + tableNames.join() + "&codeUrl=" + codeUrl + "&xmlUrl=" + xmlUrl + "&vueUrl=" + vueUrl;
            document.getElementById("url").style.display = 'none';
        },
        generator: function () {
            var tableNames = getSelectedRows();
            debugger
            var codeUrl = $("#codeUrl").val();//java代码路径
            var xmlUrl = $("#xmlUrl").val();//xml文件路径
            var vueUrl = $("#vueUrl").val();//vue文件路径
            var tablelist = tableNames.join();
            //location.href = "sys/generator/code?tables=" + tableNames.join();
            $.ajax({
                url: "sys/generator/code",
                data: {"tables": tablelist, "codeUrl": codeUrl, "xmlUrl": xmlUrl, "vueUrl": vueUrl},
                type: "GET",
                dataType: "json",
                success: function (res) {
                    if (res = "200") {
                        //alert("代码生成完成");
                        // window.location.reload();
                        alert("代码生成完成", function () {
                            window.location.reload();
                        }, 3000);
                        document.getElementById("myModal").style.display = "none";
                    } else {
                        //base_alert_time("代码生成失败，请检查相关配置!",2000);
                        //alert("代码生成失败，请检查相关配置");
                        alert("代码生成失败，请检查相关配置!", function () {
                            window.location.reload();
                        }, 3000);
                        document.getElementById("myModal").style.display = "none";
                    }
                }

            })
        }
    }
});

