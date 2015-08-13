Ext.define('app.view.ali.chart.PieChart', {
	extend: 'Ext.Panel',
	alias : 'widget.piechart',
	border : false,
    initComponent: function() {
        var me = this;
        me.items = [{
            xtype: 'polar',
            border : false,
            theme: 'default-gradients',
            width: '100%',
            height: 500,
            store: me.store,
            insetPadding: 50,
            innerPadding: 20,
//            legend: {
//                docked: 'bottom'
//            },
            interactions: ['rotate', 'itemhighlight'],
            sprites: [{
            	type: 'text',
                text: me.text,
                fontSize: 22,
                width: 100,
                height: 30,
                x: 40, // the sprite x position
                y: 20  // the sprite y position
            }],
            series: [{
                type: 'pie',
                angleField: me.angleField,
                donut: 30,
                label: {
                    field: me.groupField,
                    calloutLine: {
                        length: 60,
                        width: 3
                    },
                    renderer : function(text, sprite, config, rendererData, index){
                    	return text+': '+Ext.Number.toFixed(rendererData.store.getAt(index).get(me.angleField),2);
                    }
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get(me.groupField) + ': ' + storeItem.get(me.angleField));
                    }
                }
            }]
        }];

        this.callParent();
    }
});
