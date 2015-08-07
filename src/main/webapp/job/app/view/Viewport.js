Ext.define('Job.view.Viewport', {
    extend: 'Ext.panel.Panel',
    layout: 'border',
    items: [
            {
        region: 'west',
        collapsible : true,
    	collapsed : false,
    	split : true,
        width : 200,
        title : 'Nav',
        items : [{
        	xtype : 'nav'
        }]
    }, {
        region: 'center',
        id : 'tabpanelid',
        xtype: 'tabpanel', // TabPanel itself has no title
        activeTab: 0,      // First tab active by default
        closeAction : 'destory',
        bodyPadding : '5',
        layout : 'fit',
        items: [{
            xtype : 'jobcontainer',
            title : 'Job',
            id : 'jobcontainer'
        }]
    }
    ]
});