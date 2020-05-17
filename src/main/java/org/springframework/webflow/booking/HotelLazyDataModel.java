package org.springframework.webflow.booking;



import lombok.ToString;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.booking.entities.Hotel;

import java.util.List;
import java.util.Map;



@ToString
public class HotelLazyDataModel extends LazyDataModel<Hotel> {

	private static final long serialVersionUID = -8832831134966938627L;

	private SearchCriteria searchCriteria;

	private BookingService bookingService;

	private List<Hotel> hotels;

	private Hotel selected;


	@Autowired
	public void setBookingService(BookingService bookingService) {

		this.bookingService = bookingService;
	}

	public void setSearchCriteria(SearchCriteria searchCriteria) {

		this.searchCriteria = searchCriteria;
	}

	@Override
	public List<Hotel> load(int first, int pageSize, String sortField, SortOrder order, Map<String, Object> filters) {

		searchCriteria.setCurrentPage(first / pageSize + 1);
		hotels = bookingService.findHotels(searchCriteria, first, sortField, order.equals(SortOrder.ASCENDING));
		return hotels;
	}

	@Override
	public Hotel getRowData(String rowKey) {

		return hotels
				.stream()
				.filter(hotel -> hotel
						.getId()
						.equals(rowKey))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Object getRowKey(Hotel hotel) {

		return hotel.getId();
	}

	@Override
	public int getRowCount() {

		return bookingService.getNumberOfHotels(searchCriteria);
	}

	public Hotel getSelected() {

		return selected;
	}

	public void setSelected(Hotel selected) {

		this.selected = selected;
	}

	public int getCurrentPage() {

		return searchCriteria.getCurrentPage();
	}

	@Override
	public int getPageSize() {

		return searchCriteria.getPageSize();
	}

}