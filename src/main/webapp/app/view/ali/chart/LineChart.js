Ext.define('app.view.ali.chart.LineChart', {
    extend: 'Ext.panel.Panel',
    xtype: 'line-chart',
    layout : 'fit',
    border : false,
    initComponent: function() {
        var me = this;
        var markerFx = {
            duration: 200,
            easing: 'backOut'
        };
        
        me.items = [{
            xtype: 'cartesian',
            border : false,
            legend: {
                docked: 'bottom'
            },
            store: me.store,
            insetPadding: 40,
            sprites: [{
                type: 'text',
                text: '线性分析',
                fontSize: 22,
                width: 100,
                height: 30,
                x: 40, // the sprite x position
                y: 20  // the sprite y position
            }],
            axes: [{
                type: 'numeric',
                fields: ['count', 'duration'],
                position: 'left',
                grid: true,
                minimum: 0
//                ,
//                renderer: function (v) {
//                    return v.toFixed(2)==v?v:v.toFixed(2);
//                }
            }, {
                type: 'category',
                fields: me.groupField,
                position: 'bottom',
                grid: true,
                label: {
                    rotate: {
                        degrees: -45
                    }
                }
            }],
            series: [{
                type: 'line',
                axis: 'left',
                title: '总咨询量',
                xField: me.groupField,
                yField: 'count',
                marker: {
                    type: 'square',
                    fx: markerFx
                },
                highlightCfg: {
                    scaling: 2
                },
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    renderer: function(storeItem, item) {
                        var title = item.series.getTitle();
                        this.setHtml(title + ' for ' + storeItem.get(me.groupField) + ': ' + storeItem.get(item.series.getYField()));
                    }
                }
            }, {
                type: 'line',
                axis: 'left',
                title: '平均通话时长',
                xField: me.groupField,
                yField: 'duration',
                marker: {
                    type: 'triangle',
                    fx: markerFx
                },
                highlightCfg: {
                    scaling: 2
                },
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    renderer: function(storeItem, item) {
                        var title = item.series.getTitle();
                        this.setHtml(title + ' for ' + storeItem.get(me.groupField) + ': ' + storeItem.get(item.series.getYField()));
                    }
                }
            }]
        }];

        this.callParent();
    }
});