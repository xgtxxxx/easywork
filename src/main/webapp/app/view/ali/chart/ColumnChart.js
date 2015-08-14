Ext.define('app.view.ali.chart.ColumnChart', {
    extend: 'Ext.Panel',
    xtype: 'column-basic',
    layout : 'fit',
    border : false,
    initComponent: function () {
        var me = this;
        me.items = {
            xtype: 'cartesian',
            legend: {
                docked: 'bottom',
                frame : true
            },
//            shadow : true,
            border : false,
            store: me.store,
            insetPadding: {
                top: 40,
                bottom: 40,
                left: 20,
                right: 40
            },
            interactions: 'itemhighlight',
            axes: [{
                type: 'numeric',
                fields: ['count','duration'],
                position: 'left',
                minimum: 0,
                titleMargin: 20,
                listeners: {
                    rangechange: function (axis, range) {
                        this.setLimits({
                            value: 270,
                            line: {
                                title: {
                                    text: '270'
                                },
                                lineDash: [2,2]
                            }
                        });
                    }
                }
            }, {
                type: 'category',
                position: 'bottom'
            }],
            animation: Ext.isIE8 ? false : {
                easing: 'backOut',
                duration: 500
            },
            series: {
                type: 'bar',
                xField: me.groupField,
                yField: ['count','duration'],
                title : {
                	text : ['总电话量','平均通话时长']
                },
                stacked: false,
                style: {
                    minGapWidth: 20
                },
                highlight: {
                    strokeStyle: 'black',
                    fillStyle: 'gold',
                    lineDash: [5, 3]
                },
                label: {
                    field: ['count','duration'],
                    display: 'insideEnd',
                    renderer: function (value) {
                        return parseInt(value)===value?value:value.toFixed(2);
                    }
                }
            },
            sprites: {
                type: 'text',
                text: '数据分析',
                fontSize: 22,
                width: 100,
                height: 30,
                x: 40, // the sprite x position
                y: 20  // the sprite y position
            }
        };

        this.callParent();
    }
});