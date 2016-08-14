package com.bsp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.bsp.dao.CustomerDao;
import com.bsp.model.Customer;

public class JdbcCustomerDao implements CustomerDao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Customer customer) {
		String query = "INSERT INTO customer "
				+ "(cust_id, full_name, address, email) VALUES (?,?,?,?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, customer.getCustId());
			ps.setString(2, customer.getFullName());
			ps.setString(3, customer.getAddress());
			ps.setString(4, customer.getEmail());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException exception){
					System.out.println("Error koneksi");
				}
			}
		}
	}

	public Customer findByCustomerId(int custId) {
		String sql = "SELECT * FROM customer WHERE cust_id = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				customer = new Customer(rs.getInt("cust_id"), rs.getString("full_name"),
						rs.getString("address"),rs.getString("email"));
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn != null){
				try{
					conn.close();
				} catch (SQLException e){}
			}
		}
	}
	
	
}
