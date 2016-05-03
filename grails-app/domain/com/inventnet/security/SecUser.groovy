package com.inventnet.security

import invent.net.*

class SecUser {
    
    static hasMany = [bodegas:Bodega]

    transient springSecurityService
    
    String username
    String password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
        
    static constraints = {
        username blank: false, unique: true
        password blank: false
    }

    static mapping = {
        password column: '`password`'
        cache : true
        bodegas fetch: 'join'
    }

    Set<SecRole> getAuthorities() {
        SecUserSecRole.findAllBySecUser(this).collect { it.secRole } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            //encodePassword()
        }
    }

    public void encodePassword() {
        //println "--- Codificando"
        password = springSecurityService.encodePassword(password)
    }
}
