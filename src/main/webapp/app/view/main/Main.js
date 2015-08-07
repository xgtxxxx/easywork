/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('app.view.main.Main', {
    extend: 'Ext.container.Container',
    requires: [
//        'app.view.main.MainController',
//        'app.view.main.MainModel'
		'app.view.main.MainHeader',
		'app.view.job.JobViewport'
    ],

    xtype: 'app-main',
    
    controller: 'main',
    viewModel: {
        type: 'main'
    },

    layout: {
        type: 'border'
    },

    items: [{
    	xtype : 'mainheader',
    	region : 'north'
    },{
        xtype : 'panel',
        layout : 'fit',
        region : 'center'
    }]
});
