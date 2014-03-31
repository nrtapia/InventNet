package invent.net

class ArticulosxDocumento {

    static belongsTo = [documento: Documento]

    Articulo articulo
    Integer cantidad
    Integer id_inv

    Date dateCreated
    Date lastUpdated

    static constraints = {
        articulo(nullable: false)
        cantidad(nullable: false)
        id_inv(nullable: true)
    }

    def beforeInsert() {

        def inv = new Inventario()
        def bdg = documento.bodega
        def prv = documento.proveedor
        def art = articulo

        def criteria = Catalogo.createCriteria()
        def ctlg = criteria.get {
            eq('bodega', bdg)
            eq('proveedor', prv)
            eq('articulo', art)
        }

        inv.bodega = bdg
        inv.proveedor = prv
        inv.articulo = art
        inv.cantidad = cantidad
        inv.costo = ctlg.costo
        inv.valor = ctlg.valor
        inv.tipo = documento.tipoDocumento.movimiento.tipo
        inv.movimiento = documento.tipoDocumento.movimiento
        inv.usuario = documento.usuario
        inv.fecha = documento.fecha
        inv.control = new Date()
        inv.ref = documento.ref1

        inv.save(failOnError: true)

        if ('INP' == documento.tipoDocumento.movimiento.tipo) {
            ctlg.cantidad = (ctlg.cantidad + cantidad)
        } else {
            ctlg.cantidad = (ctlg.cantidad - cantidad)
        }
        ctlg.save(failOnError: true)

        id_inv = inv.id
    }

}
