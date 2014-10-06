package com.lantushenko.experimental.stub.web.serialize.json;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class JqGridDataWrapper {

    private int page;
    private int records;
    private int total;
    private List<AbstractDto> rows;

    public JqGridDataWrapper() {
        rows = new ArrayList<AbstractDto>();
    }

    public JqGridDataWrapper(int currentPageNumber, int recordsCount, int totalPagesCount) {
        this();
        page = currentPageNumber;
        records = recordsCount;
        total = totalPagesCount;
    }

    public void addRow(AbstractDto data) {
        rows.add(data);
    }

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("records")
    public int getRecords() {
        return records;
    }

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("rows")
    public List<AbstractDto> getRows() {
        return rows;
    }

    @JsonSerialize
    public static class JqGridRow {

        private String id;
        private AbstractDto cell;

        public JqGridRow() {
        }

        public JqGridRow(String id, AbstractDto dto) {
            this.id = id;
            this.cell = dto;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("cell")
        public AbstractDto getCell() {
            return cell;
        }
    }
}
