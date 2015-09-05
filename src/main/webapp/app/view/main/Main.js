/**
 * This class is the main view for the application. It is specified in app.js as
 * the "autoCreateViewport" property. That setting automatically applies the
 * "viewport" plugin to promote that instance of this class to the body element.
 * 
 * TODO - Replace this content of this view to suite the needs of your
 * application.
 */
Ext.define('app.view.main.Main', {
	extend : 'Ext.container.Container',
	requires : [
	'app.view.main.MainHeader'
	],
	
	uses : ['app.view.main.MainController',
	   	 'app.view.main.MainModel'],

	xtype : 'app-main',

	controller : 'main',

	layout : {
		type : 'border'
	},

	items : [ {
		xtype : 'mainheader',
		region : 'north',
		border: '0 0 2 0',
		cls : 'toobar-class',
		style: {
		    borderColor: '#9fc9f5',
		    borderStyle: 'solid'
		},
	}, {
		layout : 'fit',
		border : false,
		region : 'center',
		id     : 'main-content',
		margin : '3 0 0 0',
		items : []
	} ]
});
