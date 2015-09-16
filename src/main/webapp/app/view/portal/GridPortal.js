Ext.define("app.view.portal.GridPortal",{
	extend : 'Ext.grid.Panel',
	alias : 'widget.gridportal',
    store: [],
    columns: [
        { text: 'Name',  dataIndex: 'name' },
        { text: 'Email', dataIndex: 'email', flex: 1 },
        { text: 'Phone', dataIndex: 'phone' }
    ]
});