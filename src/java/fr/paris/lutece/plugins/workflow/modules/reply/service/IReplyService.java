/*
 * Copyright (c) 2002-2019, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.reply.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.workflow.modules.reply.business.task.Reply;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.util.ReferenceList;

/**
 *
 * IReply Service
 *
 */
public interface IReplyService
{
    // SET

    /**
     * Set the site message
     * 
     * @param request
     *            the HTTP request
     * @param strMessage
     *            the message
     * @param nTypeMessage
     *            the message type
     * @param strUrlReturn
     *            the url return
     * @throws SiteMessageException
     *             the site message
     */
    void setSiteMessage( HttpServletRequest request, String strMessage, int nTypeMessage, String strUrlReturn ) throws SiteMessageException;

    // CRUD

    /**
     * Create an reply
     * 
     * @param reply
     */
    @Transactional( ReplyPlugin.BEAN_TRANSACTION_MANAGER )
    void create( Reply reply );

    /**
     * Update an reply
     * 
     * @param reply
     */
    @Transactional( ReplyPlugin.BEAN_TRANSACTION_MANAGER )
    void update( Reply reply );

    /**
     * Find an reply
     * 
     * @param nIdHistory
     *            the id history
     * @param nIdTask
     *            the id task
     * @return a reply
     */
    Reply find( int nIdHistory, int nIdTask );

    /**
     * Find replys by a given id task
     * 
     * @param nIdTask
     *            the id task
     * @return the list of replys
     */
    List<Reply> findByIdTask( int nIdTask );

    /**
     * Remove an reply
     * 
     * @param nIdHistory
     *            the id history
     * @param nIdTask
     *            the id task
     */
    @Transactional( ReplyPlugin.BEAN_TRANSACTION_MANAGER )
    void removeByIdHistory( int nIdHistory, int nIdTask );

    /**
     * Remove an reply by id task
     * 
     * @param nIdTask
     *            the id task
     */
    @Transactional( ReplyPlugin.BEAN_TRANSACTION_MANAGER )
    void removeByIdTask( int nIdTask );

}
