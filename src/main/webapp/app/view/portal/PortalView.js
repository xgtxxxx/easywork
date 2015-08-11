Ext.define('app.view.portal.PortalView', {
	requires : [
	         'app.view.portal.RssPart',
	         'app.view.portal.PortalTemplate',
	         'app.view.portal.GridPortal'
	         ],
    extend: 'Ext.panel.Panel',
    alias : 'widget.portalview',
    border : false,
    initComponent : function() {  
    	this.items = [{
            xtype: 'dashboard',
            reference: 'dashboard',
            stateful: false,
            columnWidths: [
                0.5,
                0.5
            ],
            parts: {
                rss : 'port-rss',
                grid : {
                    viewTemplate: {
                        title: 'Grid',
                        border : false,
                        items: [{
                            xtype: 'gridportal'
                        }]
                    }
                },
            },
            defaultContent: [{
                type: 'grid',
                columnIndex: 0,
                height: 350
            }, {
                type: 'rss',
                columnIndex: 1,
                height: 350,
                feedUrl: CTX.PATH+'/portal/feed.do?data=portal2'
            },{
                type: 'rss',
                columnIndex: 0,
                height: 350,
                feedUrl: CTX.PATH+'/portal/feed.do?data=portal3'
            }, {
                type: 'rss',
                columnIndex: 1,
                height: 350,
                feedUrl: 'portal.jsp'
            }]
        }]
    	this.callParent();  
    }
});


