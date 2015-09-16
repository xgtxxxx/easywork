/**
 * This `part` implements a Google RSS Feed for use in a `Dashboard`.
 */
Ext.define('app.view.portal.RssPart', {
    extend: 'Ext.dashboard.Part',
    alias: 'part.port-rss',
    viewTemplate: {
        layout: 'fit',
        items: {
            xtype: 'portal-template',
            feedUrl: '{feedUrl}'
        }
    },
    initComponent : function() {  
    	console.log(this.viewTemplate.items)
    	this.callParent();  
    }
});
