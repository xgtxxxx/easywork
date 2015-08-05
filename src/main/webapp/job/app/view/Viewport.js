Ext.define('Job.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: 'border',
    items: [{
        region: 'north',
        xtype: 'component',
        padding: 10,
        height: 40,
        html: 'My Company - My Company Moott'
    },{
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
    }]
});