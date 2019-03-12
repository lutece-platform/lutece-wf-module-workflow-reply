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
package fr.paris.lutece.plugins.workflow.modules.reply.business.task;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ReplyDAO
 *
 */
public class ReplyDAO implements IReplyDAO
{
    private static final String SQL_QUERY_SELECT = " SELECT id_history, id_task, message " + " FROM workflow_task_reply WHERE id_history = ? AND id_task = ? ";
    private static final String SQL_QUERY_SELECT_BY_ID_TASK = " SELECT id_history, id_task, message " + " FROM workflow_task_reply WHERE id_task = ? ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO workflow_task_reply ( id_history, id_task, message ) " + " VALUES ( ?,?,? ) ";
    private static final String SQL_QUERY_DELETE_BY_ID_HISTORY = " DELETE FROM workflow_task_reply WHERE id_history = ? AND id_task = ? ";
    private static final String SQL_QUERY_DELETE_BY_TASK = " DELETE FROM workflow_task_reply WHERE id_task = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE workflow_task_reply SET message = ? WHERE id_history = ? AND id_task = ? ";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert( Reply reply, Plugin plugin )
    {
        int nIndex = 1;

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
        daoUtil.setInt( nIndex++, reply.getIdHistory( ) );
        daoUtil.setInt( nIndex++, reply.getIdTask( ) );
        daoUtil.setString( nIndex++, reply.getMessage( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store( Reply reply, Plugin plugin )
    {
        int nIndex = 1;

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setString( nIndex++, reply.getMessage( ) );

        daoUtil.setInt( nIndex++, reply.getIdHistory( ) );
        daoUtil.setInt( nIndex++, reply.getIdTask( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reply load( int nIdHistory, int nIdTask, Plugin plugin )
    {
        Reply reply = null;

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        int nIndex = 1;
        daoUtil.setInt( nIndex++, nIdHistory );
        daoUtil.setInt( nIndex++, nIdTask );

        daoUtil.executeQuery( );

        if ( daoUtil.next( ) )
        {
            nIndex = 1;

            reply = new Reply( );
            reply.setIdHistory( daoUtil.getInt( nIndex++ ) );
            reply.setIdTask( daoUtil.getInt( nIndex++ ) );
            reply.setMessage( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );

        return reply;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reply> loadByIdTask( int nIdTask, Plugin plugin )
    {
        List<Reply> listReplys = new ArrayList<Reply>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_ID_TASK, plugin );
        daoUtil.setInt( 1, nIdTask );

        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            int nIndex = 1;

            Reply reply = new Reply( );
            reply.setIdHistory( daoUtil.getInt( nIndex++ ) );
            reply.setIdTask( daoUtil.getInt( nIndex++ ) );
            reply.setMessage( daoUtil.getString( nIndex++ ) );
            listReplys.add( reply );
        }

        daoUtil.free( );

        return listReplys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByIdHistory( int nIdHistory, int nIdTask, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_BY_ID_HISTORY, plugin );
        int nIndex = 1;
        daoUtil.setInt( nIndex++, nIdHistory );
        daoUtil.setInt( nIndex++, nIdTask );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByIdTask( int nIdTask, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_BY_TASK, plugin );
        daoUtil.setInt( 1, nIdTask );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }
}
